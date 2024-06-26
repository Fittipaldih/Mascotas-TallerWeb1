package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioUsuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("repositorioUsuario")
@Transactional
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario loginUsuario(String email, String password) {

        final Session session = sessionFactory.getCurrentSession();
        Criterion rest1 = Restrictions.eq("email", email);
        Criterion rest2 = Restrictions.eq("password", password);

        return  (Usuario)session.createCriteria(Usuario.class)
                .add(rest1).add(rest2).uniqueResult();
    }

    @Override
    public void guardar(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }

    @Override
    public Usuario buscar(String email) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void modificar(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public Boolean validarEmail(String email) {

        final Session session = sessionFactory.getCurrentSession();
        Criterion rest1 = Restrictions.eq("email", email);

        Usuario encontrado = (Usuario) session.createCriteria(Usuario.class).add(rest1).uniqueResult();

        if(encontrado == null)
            return true;

        return false;

    }

    @Override
    public Usuario getUsuario(Long Id) {
        final Session session = sessionFactory.getCurrentSession();
        Criterion rest1 = Restrictions.eq("id", Id);

        Usuario encontrado = (Usuario) session.createCriteria(Usuario.class).add(rest1).uniqueResult();

        return encontrado;
    }

}
