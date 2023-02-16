# BookmicroAPI

## API 
```markdown
http://ec2-3-110-164-119.ap-south-1.compute.amazonaws.com:9900
```
## Route
* [UserDetail](https://github.com/Suraj8108/bookmicro#userDetail)
* [Booking](https://github.com/Suraj8108/bookmicro#booking)
* [Checkin](https://github.com/Suraj8108/bookmicro#checkin)
* [Fare](https://github.com/Suraj8108/bookmicro#fare)
* [Flight](https://github.com/Suraj8108/bookmicro#flight)
* [FlightBooking](https://github.com/Suraj8108/bookmicro#flightBooking)
* [JwtRequest](https://github.com/Suraj8108/bookmicro#jwtRequest)
* [JwtResponse](https://github.com/Suraj8108/bookmicro#jwtReponse)
* [OrderRazorPay](https://github.com/Suraj8108/bookmicro#orderRazorPay)
* [Passenger](https://github.com/Suraj8108/bookmicro#passenger)
* [Payment](https://github.com/Suraj8108/bookmicro#payment)
* [PnrRequest](https://github.com/Suraj8108/bookmicro#pnrRequest)
* [Route](https://github.com/Suraj8108/bookmicro#route)




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
POST /user
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/getMyDetails` |  `{"user" : json}` | Return user details.  **Required** - token |
| `/signUp` |  `{"user" : json}` | Sign up User. **Required** - userDetail model  |
| `/myCheckinLeft` |  `{"bookDto" : json}` | Return Check-In Details. **Required** - token |
| `/getMyBookings` |  `{"bookDto" : json}` | Return Booking Details. **Required** - token  |

## Booking

```javascript
User Model
{ 
    bokingId:{
            type:Number,
            required: true
    },
    pnrNo: {
        type: String,
        required: true
    },
    bookingDate: {
        type: Date,
        required: true
    },
    seatClass: {
        type: String,
        required: true
    },
    phoneNumber{
        type:Number
        required: true
    },
    flightBooking: {
        type: FlightBooking,
        required: true
    },
    payment: {
        type: Payment,
        required: true
    },
    passenger: {
        type: List,
        required: true
    }
}

```

```http
GET /book
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/getBook` |  `{"Booking" : json}` | Return all Booking List |
| `/get/{id}/passengers` |  `{"Passenger" : json}` | Return all Passenger List **Required** - Id |
| `/get/{id}/paymentDetails` |  `{"Payment" : json}` | Return payment **Required** - Id |
| `/get/{id}/fligthDetails` |  `{"flightBooking" : json}` | Return Flight details **Required** - Id |
| `/getFlightByBookId/{bookingId}` |  `{"flightBooking" : json}` | Return Flight Booking details **Required** - Booking ID |
| `/getAllPassenger` |  `{"Passenger" : json}` | Return all Passenegr List |

```http
POST /company
```

| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/bookFlight` |  `{"Booking":json}` | Book a Flight. **Required** - booking model |
| `/getByPnr` |  `{"Booking":json}` | Get Booking by PNR No. **Required** - pnr |

```http
PUT /cart
```
| Parameter| Return | Description |
| :--- | :--- | :---|
| `/set/{id}/paymentDetails` | `{"Payment":json}` | Get Payment Details. **Required** - Id |

## Checkin

```javascript
User Model
{ 
    checkinId:{
            type:Number,
            required: true
    },
    booking: {
        type: Booking,
        required: true
    },
    emailId: {
        type: String,
        required: true
    },
    flightBooking: {
        type: FlightBooking,
        required: true
    },
    payment: {
        type: Payment,
        required: true
    }
}

```

```http
GET /book
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/getAllCheckIn` |  `{"Checkin" : json}` | Return all Checkin List |


```http
POST /company
```

| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/validatePnr` |  `{"checkinDetails":json}` | Return Checkin Details. **Required** - pnrRequest model |
| `/bookingInfoForScheduler/{departureDate}` |  `{"Booking":json}` | Get Booking Details List **Required** - departureDate |
| `/addCheckin` |  `{'msg':'Successfully Added Checkin'}` | Add Check-in Details. **Required** - Checkin Model |
| `/checkInSuccess` |  `{"Checkin":json}` | Return and update Check-in Status. **Required** - Checkin Model |

## Fare

```javascript
File Model
{ 
    fareId: {
        type: Number,
        required: true
    },     
    flightNo: {
        type: Number,
        required: true
    },
    bFare : {
        type : Number, 
        required : true
    },
    eFare: {
        type: Number,
        required: true
    },
    departure :{
        type: Date,
        required: true
    }
}

```

```http
GET /fare   
```
| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/getFare/{id}/{seats}/{Class}` |  `{"Fare" : int}` | Return Fare. **Required** - Id, Seats, Class |
| `/getGst/{total}/{Class}` |  `{"GST" : int}` | Return Total GST. **Required** - Fare, Class |
| `/getTotal/{total}/{seatFare}/{gst}` |  `{"totalFare" : int}` | Return Total Fare. **Required** - Fare, seatFare, GST |
| `/get/all` | `{"Fare" : json}` | Return List of Fare |
| `/gettpyes` | `{"categories" : array({_id: product_name, count:1}) }` | Get the all unique types of the product and the count of that type of product |
| `/` |  `{"File" : json}` | Return All File |

```http
POST /file
```


| Parameter | Return | Description |
| :--- | :--- | :--- |
| `/addfare` |  `{"Fare" : json}` | Add Fare. **Required** - Fare Model |
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
                                               


