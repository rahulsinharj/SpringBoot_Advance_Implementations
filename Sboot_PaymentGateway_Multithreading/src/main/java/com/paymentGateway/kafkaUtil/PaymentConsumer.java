package com.paymentGateway.kafkaUtil;

import com.paymentGateway.entity.Payment;
import com.paymentGateway.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;

@Component
public class PaymentConsumer {

    @Autowired
    private PaymentService paymentService;

/*  In this modified code:
    - We use thenAcceptAsync on the CompletableFuture returned by paymentService.processPaymentAsync to specify what to do when the asynchronous operation completes. In this case, it prints the processed payment details.
    - We also include an exceptionally block to handle exceptions that may occur during the asynchronous operation. You can customize this part to handle errors according to your application's needs.
    - With this modification, when a payment request is received, the consumer will process it asynchronously and print the processed payment details when the operation completes.
 */
    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)    // containerFactory = "kafkaListenerContainerFactory")
    public void receivePaymentRequest(Payment paymentRequest)
    {
        System.out.println("******Msg RECEIVED on Kafka topic : Received payment request: " + paymentRequest.toString());
        // Validate the payment (add your validation logic here)

        // Save the validated payment to the database and handle the CompletableFuture result
        paymentService.processPaymentAsync(paymentRequest)
                .thenAcceptAsync(savedPayment -> {
                    System.out.println("Processed payment request: "+savedPayment.toString());
                    System.out.println("Payment ID: " + savedPayment.getId());
                    System.out.println("Card Number: " + savedPayment.getCardNumber());
                    System.out.println("Card Holder: " + savedPayment.getCardHolder());
                    System.out.println("Amount: " + savedPayment.getAmount());
                })
                .exceptionally(ex -> {
                    // Handle exceptions if needed
                    ex.printStackTrace();
                    return null;
                });
    }


/*  In this modified code:
    - We use the join() method on the CompletableFuture returned by paymentService.processPaymentAsync(paymentRequest) to wait for the asynchronous operation to complete and reassign the savedPayment variable.
    - After reassigning savedPayment, we print the processed payment details by accessing its properties using get().
    - Please note that using join() in this way will block the execution of this method until the asynchronous operation completes. If you want non-blocking behavior, you can use the thenAccept method as shown in previous responses.
 */
/*
@KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)    // containerFactory = "kafkaListenerContainerFactory")
    public void receivePaymentRequest(Payment paymentRequest)
    {
        System.out.println("******Msg RECEIVED on Kafka topic : Received payment request: " + paymentRequest.toString());
        // Validate the payment (add your validation logic here)

        // Save the validated payment to the database and reassign the savedPayment
        try {
            CompletableFuture<Payment> savedPayment = paymentService.processPaymentAsync(paymentRequest);

            // Wait for the CompletableFuture to complete and reassign savedPayment
            savedPayment.join();

            System.out.println("Processed payment request: " + savedPayment.get().toString());

            System.out.println("Payment ID: " + savedPayment.get().getId());
            System.out.println("Card Number: " + savedPayment.get().getCardNumber());
            System.out.println("Card Holder: " + savedPayment.get().getCardHolder());
            System.out.println("Amount: " + savedPayment.get().getAmount());
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
*/


/*  // Only processPaymentAsync , not showing the comsumed value after storing

    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)  // containerFactory = "kafkaListenerContainerFactory")
    public void receivePaymentRequest(Payment paymentRequest)
    {
        System.out.println("******Msg RECEIVED on Kafka topic : Received payment request: " + paymentRequest.toString());
        // Validate the payment (add your validation logic here)

        // Save the validated payment to the database
        CompletableFuture<Payment> savedPayment = null;
        try {
            savedPayment = paymentService.processPaymentAsync(paymentRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Processed payment request: " + savedPayment.toString());
    }
*/



/*  //  Parse the payment request (assuming it's a JSON or a specific format)

    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)  // containerFactory = "kafkaListenerContainerFactory")
    public void receivePaymentRequest(String paymentRequest)
    {
        // Parse the payment request (assuming it's a JSON or a specific format)
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Payment payment = objectMapper.readValue(paymentRequest, Payment.class);
            System.out.println("******Msg RECEIVED on Kafka topic : Received payment request: " + paymentRequest.toString());
            // Validate the payment (add your validation logic here)

            // Save the validated payment to the database
            CompletableFuture<Payment> savedPayment = paymentService.processPaymentAsync(payment);

            System.out.println("Processed payment request: " + savedPayment.toString());
        } catch (IOException e) {
            System.err.println("Error processing payment request: " + e.getMessage());
        }
    }*/
}
