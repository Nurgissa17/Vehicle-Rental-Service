package src.adapter;
public class PaymentAdapter implements Payment {
    private OldPaymentService service;
    public PaymentAdapter(OldPaymentService service) { this.service = service; }
    public void processPayment(double amount) { service.pay(amount); }
}
