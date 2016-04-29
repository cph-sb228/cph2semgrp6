package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.User;
import java.util.ArrayList;
import java.util.List;
import domain.PolygonException;
import dataaccess.UsersMapper;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author terfy
 */
public class UsersMapperTest {
    int i;
    List<User> users;
    User user;
    String s;
    
    public UsersMapperTest() {
        
        
    }
    
    @Test
    public void testGetUser() throws PolygonException{
        System.out.println("UsersMapper.GetUser()");
        System.out.println("brugere i DB:");
        users = UsersMapper.getUser();
        assertTrue(users.size()>0);
        for(User user : users){
            assertTrue(user.getUsername().length()>0);
            System.out.println(user.getUsername());
        }
    }
    
    @Test
    public void testInsertUser() throws PolygonException{
        System.out.println("\nUsersMapper.InsertUser()");
        users = UsersMapper.getUser();
        i = users.size();
        System.out.println("Før: " + i);
        UsersMapper.insertUser(this.user);
        System.out.println("Bruger oprettet: " + user.getUsername());
        users = UsersMapper.getUser();
        System.out.println("Efter: " + users.size());
        assertFalse(users.size()==i);
    }
    
    @Test
    public void testRemoveUser() throws PolygonException{
        System.out.println("\nUsersMapper.removeUser()");
        users = UsersMapper.getUser();
        i = users.size();
        System.out.println("Før: " + i);
        int m = 0;
        for(User user1 : users){
            if(user1.getUsername().equals(user.getUsername())) m = user1.getId();
            UsersMapper.removeUser(m);
        }
        users = UsersMapper.getUser();
        System.out.print("Efter: ");
        System.out.println(users.size());
        assertFalse(users.size()== i);
        System.out.println("brugere i DB:");
        for(User user : users){
            System.out.println(user.getUsername());
        }
    }
    
    
    
    
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        users = new ArrayList<>();
        this.user = new User("TESTUSER", "kodenoget", "noget@nu.dk", "customer");
        s = "";
        i = 0;
    }
    
    @After  
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
