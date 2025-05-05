package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import setup.ItemModel;
import setup.UserModel;
import setup.UserUpdateModel;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController {
     Properties prop;

    public UserController(Properties prop){
        this.prop = prop;
    }

    public  Response doRegistration(UserModel userModel){
        RestAssured.baseURI = prop.getProperty("baseUrl");

        return given().contentType("application/json")
                .body(userModel)
                .when().post("/api/auth/register");

    }

    public Response doAdminLogin(UserModel userModel){
        RestAssured.baseURI = prop.getProperty("baseUrl");

        return given().contentType("application/json")
                .body(userModel)
                .when().post("/api/auth/login");

    }

    public Response getUserList(){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        return given().contentType("application/json").
                header("Authorization","Bearer "+prop.getProperty("token"))
                .when().get("/api/user/users");
    }

    public Response searchUser(String id){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        return given().contentType("application/json").
                header("Authorization","Bearer "+prop.getProperty("token"))
                .when().get("/api/user/"+id);

    }

    public Response editUserInfo(String userid, UserUpdateModel updateModel){
        RestAssured.baseURI= prop.getProperty("baseUrl");
        return given().contentType("application/json").body(updateModel)
                .header("Authorization","Bearer "+ prop.getProperty("token"))
                .when().put("/api/user/"+ userid);

    }


    // user activity....
    public Response doUserLogin(UserModel userModel){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        return given().contentType("application/json").body(userModel)
                .when().post("/api/auth/login");

    }

    public Response addItem(ItemModel itemModel){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body(itemModel)
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .when().post("/api/costs");

    }

    public Response getItemList(){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        return given().contentType("application/json")
                . header("Authorization","Bearer "+prop.getProperty("userToken"))
                .when().get("/api/costs");

    }

    public Response editItemName(String itemid, ItemModel itemModel){
        RestAssured.baseURI= prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body(itemModel)
                .header("Authorization","Bearer "+ prop.getProperty("userToken"))
                .when().put("/api/costs/"+ itemid);

    }

    public Response deleteItem(String itemid){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .when().delete("/api/costs/"+itemid);
    }






}
