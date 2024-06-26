package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.EmailEnUsoException;
import com.tallerwebi.dominio.excepcion.PasswordLenghtException;
import com.tallerwebi.dominio.excepcion.PasswordsDiferentesException;
import com.tallerwebi.dominio.servicios.DataUsuario;
import com.tallerwebi.dominio.servicios.interfaces.ServicioSession;
import com.tallerwebi.dominio.servicios.interfaces.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorUsuario {

    private ServicioUsuario servicioUsuario;
    private ServicioSession servicioSession;
    @Autowired
    public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioSession servicioSession){
        this.servicioUsuario = servicioUsuario;
        this.servicioSession = servicioSession;
    }


    @RequestMapping("/login")
    public ModelAndView irALogin(HttpServletRequest request,final RedirectAttributes redirectAttributes) {

        ModelMap model = new ModelMap();

        if(null!=obtenerUsuarioLogueado(request)) {
            redirectAttributes.addFlashAttribute("mensaje","Ya esta Logueado!"); //permite redirect con model
            return new ModelAndView("redirect:/home");
        }

        model.put("dataUsuario", new Usuario());

        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("dataUsuario") DataUsuario dataUsuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioUsuario.loginUsuario(dataUsuario.getEmail(), dataUsuario.getPassword());

        if (usuarioBuscado != null) {

            servicioSession.setUserId(usuarioBuscado.getId(), request);
            return new ModelAndView("redirect:/home");

        } else if(usuarioBuscado != null ) {

            servicioSession.setUserId(usuarioBuscado.getId(), request);
            return new ModelAndView("redirect:/elegir-gustos");
        }else{
            model.put("error", "Usuario o clave incorrecta!");
        }
        return new ModelAndView("login", model);
    }

    @RequestMapping("/registrarme")
    public ModelAndView registro(HttpServletRequest request, final RedirectAttributes redirectAttributes) {

        ModelMap model = new ModelMap();

        if(null!=obtenerUsuarioLogueado(request)) {
            redirectAttributes.addFlashAttribute("mensaje","Ya esta Logueado!");
            return new ModelAndView("redirect:/home");
        }

        model.put("dataUsuario", new DataUsuario());

        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(path="/procesarRegistro",method=RequestMethod.POST)
    public ModelAndView procesarRegistro(
            @ModelAttribute("dataUsuario") DataUsuario dataUsuario, final RedirectAttributes redirectAttributes) {

        String mensaje="Se Registro Exitosamente!";

        try {
            validarPassword(dataUsuario.getPassword(),dataUsuario.getPasswordRe());
        } catch (PasswordLenghtException e) {
            mensaje="La contraseña debe tener por lo menos 12 caracteres!";
        } catch (PasswordsDiferentesException e) {
            mensaje="La contraseñas deben ser iguales!";
        }

        try {
            servicioUsuario.registrarUsuario(dataUsuario.getEmail(),dataUsuario.getPassword(),dataUsuario.getNombre());
        } catch (EmailEnUsoException e) {
            mensaje="El Email ya esta en uso!";
        }

        if(mensaje!="Se Registro Exitosamente!") {
            redirectAttributes.addFlashAttribute("mensaje",mensaje);
            return new ModelAndView("redirect:/registrarme");
        }

        redirectAttributes.addFlashAttribute("mensaje",mensaje);
        return new ModelAndView("redirect:/login");

    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request,@ModelAttribute("error") String mensaje) {

        ModelMap model = new ModelMap();
        Usuario usuario = obtenerUsuarioLogueado(request);

        model.put("usuario",usuario);

        return new ModelAndView("home",model);
    }

    @RequestMapping(path = "/perfil", method = RequestMethod.GET)
    public ModelAndView verPerfil(HttpServletRequest request, final RedirectAttributes redirectAttributes) {

        ModelMap model = new ModelMap();

        if(null==obtenerUsuarioLogueado(request)) {
            redirectAttributes.addFlashAttribute("mensaje","No puede ver su perfil sin estar logueado!");
            return new ModelAndView("redirect:/home");
        }

        model.put("dataUsuario", obtenerUsuarioLogueado(request));

        return new ModelAndView("perfil-usuario", model);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView guardarUsuario(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }

    private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
        return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
    }

    public void validarPassword(String password, String passwordRe) {
        validarPasswordLenght(password);
        validarPasswordSimilitud(password,passwordRe);
    }

    public void validarPasswordLenght(String password) throws PasswordLenghtException {
        if(password.length()<12)
            throw new PasswordLenghtException();
    }

    public void validarPasswordSimilitud(String password,String passwordRe) throws PasswordsDiferentesException{
        if(!(password.equals(passwordRe)))
            throw new PasswordsDiferentesException();
    }

}