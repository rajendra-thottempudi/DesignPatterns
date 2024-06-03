package Behavioral.ChainOfResponsibilityExample.ATMExample;

//The base interface should have a method to define the next processor in the chain and the method that will process the request.
/*The important point to note here is the implementation of dispense method.
You will notice that every implementation is trying to process the request and based on the amount, it might process some or full part of it.
If one of the chain not able to process it fully, it sends the request to the next processor in chain to process the remaining request.
If the processor is not able to process anything, it just forwards the same request to the next chain.
*/


public interface DispenseChain {

    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}
