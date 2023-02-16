# BookmicroAPI

## API 
```markdown
http://ec2-3-110-164-119.ap-south-1.compute.amazonaws.com:9900
```
## Route
* [Booking](https://github.com/Abh3201/FlipXoAPI#user)
* [Checkin](https://github.com/Abh3201/FlipXoAPI#company)
* [Fare](https://github.com/Abh3201/FlipXoAPI#file)
* [Flight](https://github.com/Abh3201/FlipXoAPI#cart)
* [FlightBooking](https://github.com/Abh3201/FlipXoAPI#order)
* [JwtRequest](https://github.com/Abh3201/FlipXoAPI#payment)
* [JwtResponse](https://github.com/Abh3201/FlipXoAPI#sentiment)
* [OrderRazorPay](https://github.com/Abh3201/FlipXoAPI#sentiment)
* [Passenger](https://github.com/Abh3201/FlipXoAPI#sentiment)
* [Payment](https://github.com/Abh3201/FlipXoAPI#sentiment)
* [PnrRequest](https://github.com/Abh3201/FlipXoAPI#sentiment)
* [Route](https://github.com/Abh3201/FlipXoAPI#sentiment)
* [UserDetail](https://github.com/Abh3201/FlipXoAPI#sentiment)



## UserDetail

```javascript
User Model
{ 
    userId:{
            type:Number,
            required: true
    },
    firstName: {
        type: String,
        required: true
    },
    lastName: {
        type: String,
        required: true
    },
    emailId: {
        type: String,
        required: true
    },
    phoneNumber{
        type:Number
        required: true
    },
    password: {
        type: String,
        required: true
    },
    loyalty: {
        type: Number,
        required: true
    }
}

```

```http
GET /user
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/getMyDetails` |  `{"success" : bool, "user" : json}` | Return user details by Token |
| `/signUp` |  `{"success" : bool, "user" : json}` | Return user by ID |

```http
POST /user
```

| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/register` |  `{"success" : bool, "msg":'user registered'}` | Register User. **Required** - user model |
| `/authenticate` |  `{"success" : bool, ""token" : "JWT Token", "user" : json}` | Authenticate user. **Required** - email and password in body |

## Company

```javascript
Company Model
{ 
    name: {
        type: String,
        required: true
    },      
    email: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    }
}

```

```http
GET /company
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/` |  `{"success" : bool, "company" : json}` | Return All Company |
| `/:id` |  `{"success" : bool, "company" : json}` | Return company by ID |

```http
POST /company
```

| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/register` |  `{"success" : bool, "msg":'company registered'}` | Register company. **Required** - company model |
| `/authenticate` |  `{"success" : bool, "token" : "JWT Token", "company" : json}` | Authenticate company. **Required** - email and password in body |

## File

```javascript
File Model
{ 
    name: {
        type: String,
        required: true
    },     
    companyId: {
        type: Schema.ObjectId,
        required: true
    },
    type : {
        type : String, 
        required : true
    },
    link: {
        type: String,
        required: true
    },
    description :{
        type: String,
        required: true
    },
    instock:{
        type: Number,
        required: true
    },
    price:{
        type: Number,
        required: true
    },
    weight:{
        type: Number,
        required: true
    }

}

```

```http
GET /file
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/` |  `{"File" : json}` | Return All File |
| `/type/:type` |  `{"Type" : "TypeName", "File" : json}` | Return file by type |
| `/id/:id` |  `{"id" : "id", "File" : json}` | Return file by id |
| `/comid/:comid` | `{"Company Id" : comId , "Product" : json}` | Return files by Company ID |
| `/gettpyes` | `{"categories" : array({_id: product_name, count:1}) }` | Get the all unique types of the product and the count of that type of product |

```http
POST /file
```

| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/upload` |  `{Done}` | Upload File. **Required** - File model |

## Cart

```javascript
Cart Model
{ 
    ProductID: {
        type: Schema.ObjectId,
        required: true
    },      
    UserID: {
        type: Schema.ObjectId,
        required: true
    },
    Quantity: {
        type: Number,
        required: true
    }
}

```

```http
GET /cart
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/user/:userID` |  `{"userID" : userID, "Cart" : json}` | Return Cart by userID |
| `/user/:userID/:productID` |  `{"userID" : userID, "Cart" : json}` | Return Cart by userID and ProductID|
| `/delete/:cartId`| `{"cartID" : cartId, "deleteCount" : response.deletedCount, }` | Delete the product from cart |

```http
POST /cart
```

| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/add` |  `{Done}` | Add in cart. **Required** - Cart model |

```http
PUT /cart
```
| Parameter| Return | Description |
| :--- | :--- | :---|
| `/update`| {"cartDetails": cartDetails, "msg":"Updated Successfully" } | Update the cart product quantity. **Required** - the json of key value pair where key is cart id and value is the quantity value that needs to be updated eg{ 464gdv46:2, 33ee44e:4}|

## Order

```javascript
Order Model
{ 
    name : {
        type: String,
        required: true
    },
    phone_no : {
        type: String,
        required: true
    },
    email : {
        type: String,
        required: true
    },
    country :{
        type: String,
        required: true
    },
    street : {
        type: String,
        required: true
    },
    city : {
        type: String,
        required: true
    },
    zip : {
        type: String,
        required: true
    },
    message : {
        type: String,
        required: false
    },
    user_id: {
        type: Schema.ObjectId,
        required: true
    },     
    product_id: {
        type: Array,
        required: true
    },
    lat :{
        type: String,
        required: true
    },
    long:{
        type: String,
        required: true
    },
    transcationID:{
        type: String,
        required: true
    },
    delivery_status:{
        type: String,
        default: 'Soon'
    },
    transcation_status:{
        type: Boolean,
        required: true
    },
    orderId : {
        type : String,
        required : true
    }

}

```

```http
GET /order
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/:orderId` |  `{orderId : orderId , OrderDetails : json}` | Return whole order details of that orderID (Response will be in OrderModel json)  |      
| `/user/:userID` |  `{"userID" : userID, "Orders" : json}` | Return Order by userID |
| `/user/transcation_status/:userID/:transcation_status` |  `{"userID" : userID, "Orders" : json}` | Return Order by userID and transcation_status |
| `/user/delivery_status/:userID/:delivery_status` |  `{"userID" : userID, "Orders" : json}` | Return Order by userID and delivery_status |
| `/company/:companyID` |  `{"companyID" : companyID, "Orders" : json}` | Return Order by companyID |
| `/company/transcation_status/:companyID/:transcation_status` |  `{"companyID" : companyID, "Orders" : json}` | Return Order by companyID and transcation_status |
| `/company/delivery_status/:companyID/:delivery_status` |  `{"companyID" : companyID, "Orders" : json}` | Return Order by companyID and delivery_status |

```http
POST /order
```

| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/add` |  `{Done}` | Add in Order. **Required** - Order model |


## Payment


```http
GET /payment
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/` |  Response   | For Payment. **Required** - orderId and price should be pass in **headers** |
| `/:order_id`|`{order_id: order_id, payment: payment_details}`| Get the payment details by the orderId **Required order_id**

## Sentiment
```javascript
Response
{
        KeyWord  : String,
        Positive : Number,
        Negative : Number,
        Neutral  : Number,
        Count    : 100,
        Tweets   : [String, String, String, String, String],
        PWC      : [{}, {}......],
        NWC      : [{}, {}......]
  }


```


```http
GET /find
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/:keyword` |  Response   | Return sentiment of keyword |
                                               


