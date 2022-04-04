package edu.escuelaing.arep.segurdiadLogin.entities;

import java.util.HashMap;
import java.util.Map;

public class UserDAO implements IPersistenceDAOUser {
    private Map<String,String> usuarios;
    //Admin123:3b612c75a7b5048a435fb6ec81e52ff92d6d795a8b5a9c17070f6a63c97a53b2
    //password123:ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f
    public UserDAO(){
        usuarios = new HashMap<>();
        usuarios.put("usuario1","3b612c75a7b5048a435fb6ec81e52ff92d6d795a8b5a9c17070f6a63c97a53b2");
        usuarios.put("usuario2","ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f");
    }

    @Override
    public String LoadPassByUser(String user) {
        return usuarios.get(user);
    }

    @Override
    public Map<String, String> loadUsers() {
        return usuarios;
    }
}
