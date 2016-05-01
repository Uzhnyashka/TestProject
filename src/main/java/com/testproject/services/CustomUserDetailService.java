package com.testproject.services;

import com.testproject.DAO.Impl.UserDAOImpl;
import com.testproject.DAO.UserDAO;
import com.testproject.objects.UserObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bobyk on 01/05/16.
 */
public class CustomUserDetailService implements UserDetailsService {

    private UserDAOImpl userDAO = new UserDAOImpl();
    private UserObject userObject = null;
    private static UserDetails user;

    @SuppressWarnings("deprecation")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            userObject = userDAO.getUserByLogin(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userObject == null){
            throw new UsernameNotFoundException("username " + username + " not found");
        }
        Collection<GrantedAuthority> gr = new ArrayList<GrantedAuthority>();
        if (userObject.getRole().equals("admin")) gr.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        else gr.add(new GrantedAuthorityImpl("ROLE_USER"));
        user = new User(username, userObject.getPassword(), true, true, true, true, gr);
        return user;
    }

    public static String getUserName(){
        return user.getUsername();
    }
}
