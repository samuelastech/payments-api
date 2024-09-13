package github.samuelastech.payment;

import github.samuelastech.order.OrderClient;
import github.samuelastech.payment.dtos.PaymentDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrderClient order;

    public Page<PaymentDTO> getAll(Pageable paging) {
        return repository
                .findAll(paging)
                .map(payment -> mapper.map(payment, PaymentDTO.class));
    }

    public PaymentDTO getById(Long id) {
        Payment payment = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO create(PaymentDTO paymentDTO) {
        Payment payment = mapper.map(paymentDTO, Payment.class);
        payment.setStatus(PaymentStatus.CREATED);
        repository.save(payment);
        return mapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO update(Long id, PaymentDTO paymentDTO) {
        Payment payment = mapper.map(paymentDTO, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);
        return mapper.map(payment, PaymentDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void confirmPaymentWithoutIntegration(Long id) {
        updateStatusAndSave(id, PaymentStatus.CONFIRMED_WITHOUT_INTEGRATION);
    }

    public void confirmPayment(Long id) {
        updateStatusAndSave(id, PaymentStatus.CONFIRMED);
        order.updatePayment(id);
    }

    private void updateStatusAndSave(Long id, PaymentStatus status) {
        Payment payment = repository.findById(id).orElseThrow();
        payment.setStatus(status);
        repository.save(payment);
    }
}
