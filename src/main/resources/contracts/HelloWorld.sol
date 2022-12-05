//Specifies the version of solidity
pragma solidity ^0.8.17;

//Declares a contract named Hello World
contract HelloWorld{

    //Declare a string state variable called message
    string public message;

    //Declares a constructor that will used to initialize the contract's data, which takes an 'initMessage' of type string memory
    constructor(string memory initMessage) {

        //Accepts the string message and set the value to the contract's 'message' variable.
        message = initMessage;
    }

    //A public function 'updateMessage' that accepts a string argument and sets the value of the newMessage to the `message` storage variable.
    function updateMessage(string memory newMessage) public {
        message = newMessage;
    }
    // A public function that returns the value of the 'message' storage variable
    function getMessage()public view returns (string memory){
        return message;
    }

}