package testRunner;

import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.ItemModel;
import setup.Setup;
import setup.UserModel;
import setup.UserUpdateModel;
import utils.Utils;

public class UserTestRunner extends Setup {
    private UserController userController;

   @BeforeMethod
    public void initUserController(){
        userController = new UserController(prop);
    }

    @Test(priority = 1, description = "Negative Test: Register user with missing fields")
    public void registerUserWithMissingFields(){

        UserModel userModel = new UserModel();  // Empty user data

        Response res = userController.doRegistration(userModel);
        System.out.println(res.asString());

        Assert.assertEquals(res.getStatusCode(), 500);
        Assert.assertTrue(res.asString().contains("Server error"));
    }

    @Test(priority = 2, description = "User Registration")
    public void doRegistration() throws ConfigurationException {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "nadim.cse.edu+" + Utils.generateRandomNumber(1000, 9999) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "0120" + Utils.generateRandomNumber(1000000, 9999999);
        String address = faker.address().fullAddress();
        String gender = faker.options().option("Male", "Female"); // Randomly selects a gender

        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        userModel.setGender(gender);
        userModel.setTermsAccepted(true);

        Response res = userController.doRegistration(userModel);
        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();

        String userId = jsonPath.get("_id");
        Utils.setEnver("UserId", userId);

        String userFirstName = jsonPath.get("firstName");
        Utils.setEnver("userFirstName", userFirstName);

        String userLastName = jsonPath.get("lastName");
        Utils.setEnver("userLastName", userLastName);

        String userEmail = jsonPath.get("email");
        Utils.setEnver("userEmail", userEmail);

        Utils.setEnver("userPassword", password);
        Utils.setEnver("userPhoneNumber",phoneNumber);
        Utils.setEnver("userAddress",address);
        Utils.setEnver("gender", gender);


    }

    @Test(priority = 3, description = "Negative Test: Register with Existing Credentials")
    public void registerWithExistingEmail(){

        UserModel userModel = new UserModel();
        userModel.setFirstName("Test");
        userModel.setLastName("User");
        userModel.setEmail(prop.getProperty("userEmail")); // Reusing same email
        userModel.setPassword("Password123");
        userModel.setPhoneNumber("01500000000");
        userModel.setAddress("Test Address");
        userModel.setGender("Female");
        userModel.setTermsAccepted(true);
        Response res = userController.doRegistration(userModel);
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 400); // Expecting failure
        Assert.assertTrue(res.asString().contains("User already exists with this email address"));
    }


    // Admin activity starts...........

    @Test(priority = 4, description = "Negative Test: Admin login with invalid credentials")
    public void invalidAdminLogin(){

       UserModel userModel = new UserModel();
       userModel.setEmail("admin@test.com");
       userModel.setPassword("wrongpass");
       Response res = userController.doAdminLogin(userModel);
       System.out.println(res.asString());
       Assert.assertEquals(res.getStatusCode(), 401);
       Assert.assertTrue(res.asString().contains("Invalid email or password"));

   }


   @Test(priority = 5, description = "Admin Login")
    public void adminLogin() throws ConfigurationException, InterruptedException {

        UserModel userModel = new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("admin123");
        Response res = userController.doAdminLogin(userModel);
        System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.get("token");
        System.out.println("token: " + token);
        Utils.setEnver("token", token);
        Thread.sleep(2000);

    }

    @Test(priority = 6, description = "Get User List")
    public void getUserList(){

        Response res = userController.getUserList(); // Fixed the method call
        System.out.println(res.asString());

    }

    @Test(priority = 7, description = "Search User")
    public void searchUser(){

        Response res = userController.searchUser(prop.getProperty("UserId"));
        System.out.println(res.asString());

    }

   @Test(priority = 8, description = "Edit User FirstName, lastName and PhoneNumber")
    public void editUserInfo() throws ConfigurationException {

                UserUpdateModel updateModel = new UserUpdateModel();
                String firstName="Mr";
                updateModel.setFirstName(firstName);
                Utils.setEnver("userFirstName",firstName);

               String lastName="ABC";
               updateModel.setLastName(lastName);
               Utils.setEnver("userLastName",lastName);

                String phoneNumber = "01603333333";
                updateModel.setPhoneNumber(phoneNumber);
                Utils.setEnver("userPhoneNumber",phoneNumber);

                String id= prop.getProperty("UserId");
                String email = prop.getProperty("userEmail");
                updateModel.setEmail(email);
                String address = prop.getProperty("userAddress");
                updateModel.setAddress(address);
                String gender = prop.getProperty("gender");
                updateModel.setGender(gender);

                Response responseNew = userController.editUserInfo(id, updateModel);
                System.out.println(responseNew.asString());



    }

    // User activity...................................

    @Test(priority = 9, description = "Negative Test: User login with wrong password")
    public void invalidUserLogin(){

        UserModel userModel = new UserModel();
        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPassword("WrongPassword123");
        Response res = userController.doUserLogin(userModel);
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 401);
        Assert.assertTrue(res.asString().contains("Invalid email or password"));

    }

    @Test(priority = 10, description = "User Login")
    public void userLogin() throws ConfigurationException {

        UserModel userModel=new UserModel();
        userModel.setEmail(prop.getProperty("userEmail"));
        System.out.println(prop.getProperty("userEmail"));

        userModel.setPassword(prop.getProperty("userPassword"));
        System.out.println(prop.getProperty("userPassword"));

        Response response= userController.doUserLogin(userModel);
        System.out.println(response.asString());
        JsonPath jsonPath=response.jsonPath();
        String userToken=jsonPath.get("token");
        System.out.println(userToken);
        Utils.setEnver("userToken",userToken);

    }

    @Test(priority = 11, description = "Get Item List")
    public void getItemList(){

        Response res = userController.getItemList();
        System.out.println(res.asString());

    }

    @Test(priority = 12, description = "Add Item")
    public void addItem() throws ConfigurationException {

        ItemModel itemModel = new ItemModel();
        itemModel.setItemName("Food");
        itemModel.setQuantity("4");
        itemModel.setAmount("5000");
        itemModel.setPurchaseDate("2025-04-19");
        itemModel.setMonth("April");
        itemModel.setRemarks("Dinner");
        Response res = userController.addItem(itemModel);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String itemId = jsonPath.get("_id");
        System.out.println(itemId);
        Utils.setEnver("ItemId", itemId);
        String itemName = jsonPath.get("itemName");
        Utils.setEnver("ItemName", itemName);

    }


    @Test(priority = 13, description = "Edit Item Name")
    public void editItemName(){

        ItemModel itemModel = new ItemModel();
        itemModel.setItemName("Fast Food");
        itemModel.setQuantity("4");
        itemModel.setAmount("5000");
        itemModel.setPurchaseDate("2025-04-19");
        itemModel.setMonth("April");
        itemModel.setRemarks("Dinner");
        Response responseNew = userController.editItemName(prop.getProperty("ItemId"), itemModel);
        System.out.println(responseNew.asString());

    }


    @Test(priority = 14, description = "Delete Item")
    public void deleteItem(){

        Response responseNew = userController.deleteItem(prop.getProperty("ItemId"));
        System.out.println(responseNew.asString());
    }








}
