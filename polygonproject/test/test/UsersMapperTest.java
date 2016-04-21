package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.User;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import model.PolygonException;
import model.UsersMapper;
import org.junit.After;
import org.junit.AfterClass;
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
        System.out.println("\ntestGetUser()");
        System.out.println("brugere i DB:");
        users = UsersMapper.getUser();
        Assert.assertTrue(users.size()>0);
        for(User user : users){
            Assert.assertTrue(user.getUsername().length()>0);
            System.out.println(user.getUsername());
        }
    }
    
    @Test
    public void testInsertUser() throws PolygonException{
        System.out.println("\ntestInsertUser()");
        users = UsersMapper.getUser();
        i = users.size();
        System.out.println("Før: " + i);
        UsersMapper.insertUser(this.user);
        System.out.println("Bruger oprettet: " + user.getUsername());
        users = UsersMapper.getUser();
        System.out.println("Efter: " + users.size());
        Assert.assertFalse(users.size()==i);
    }
    
    @Test
    public void testRemoveUser() throws PolygonException{
        System.out.println("\ntestRemoveUser()");
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
        Assert.assertFalse(users.size()==i);
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
