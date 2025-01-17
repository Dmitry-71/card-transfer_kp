package ru.netology.cardtransfer_kp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cardtransfer_kp.request.ConfirmOperationRequest;
import ru.netology.cardtransfer_kp.request.TransferRequest;
import ru.netology.cardtransfer_kp.response.TransferResponse;
import ru.netology.cardtransfer_kp.service.TransferService;

@RestController
@CrossOrigin
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest request) {
        TransferResponse response = new TransferResponse();
        response.setOperationId(service.transfer(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<TransferResponse> confirmOperation(@Valid @RequestBody ConfirmOperationRequest request) {
        TransferResponse response = new TransferResponse();
        response.setOperationId(service.confirmOperation(request));
        return ResponseEntity.ok(response);
    }
}
