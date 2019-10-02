package cn.itcast.security;

import cn.itcast.POJO.Permission;
import cn.itcast.POJO.Role;
import cn.itcast.POJO.User;
import cn.itcast.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
         User user = userService.findUserByName(username);
        System.out.println(user);
         if (user==null) return null;
         List<SimpleGrantedAuthority> security = new ArrayList<>();

        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            security.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                security.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        org.springframework.security.core.userdetails.User securityUser
                = new org.springframework.security.core.userdetails.User(username,user.getPassword(),security);
        return securityUser;
    }
}
