package edu.escuelaing.arep.segurdiadLogin.entities;

import java.util.Map;

public interface IPersistenceDAOUser {

    public String LoadPassByUser(String user);

    public Map<String, String> loadUsers();
}
