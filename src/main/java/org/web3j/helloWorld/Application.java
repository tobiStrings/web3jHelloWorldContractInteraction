package org.web3j.helloWorld;

import org.web3j.crypto.Credentials;
import org.web3j.helloWorld.contracts.HelloWorld;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;

public class Application {
    //Declaring the private key variable whose value is copied from one of the ganache addresses ganache generated.
    private static final String PRIVATE_KEY = "<the private key from the ganache address you have copied>";

    //Declaring a gas limit variable
    //A Gas limit is the maximum amount of work you're estimating a validator will do on a particular transaction
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);

    //Declaring a gas price variable
    //A Gas price is the amount of computational power needed for executing specific operations on the network
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    public static void main(String[] args) {

        //Crating an instance of the web3j library and connecting to the ganache local network
        Web3j web3 = Web3j.build(new HttpService()); // localhost:8545 the default

        //Creating an instance of the transaction manager passing the web instance created and credentials generated from the private key
        TransactionManager transactionManager = new RawTransactionManager(web3, Credentials.create(PRIVATE_KEY));

        try {
            // Deploying the contract on the local network
            HelloWorld helloWorld = HelloWorld.deploy(web3,transactionManager,GAS_PRICE,GAS_LIMIT,"Hello World").send();

            //logging the initial message deployed with the contract
            System.out.println("The initial message deployed with the contract is '"+helloWorld.getMessage().send()+"'");

            //Updating the message in the contract
            helloWorld.updateMessage("My first interaction with Ethereum").send();

            //logging the updated message in the contract
            System.out.println("The updated message of the deployed contract is '"+helloWorld.getMessage().send()+"'");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
