package ru.netology.cardtransfer_kp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.cardtransfer_kp.exception.InputDataException;
import ru.netology.cardtransfer_kp.repository.TransferRepository;
import ru.netology.cardtransfer_kp.request.ConfirmOperationRequest;
import ru.netology.cardtransfer_kp.request.TransferRequest;

import static ru.netology.cardtransfer_kp.global.Messages.CONFIRM_CODE_IS_NOT_VALID;
import static ru.netology.cardtransfer_kp.global.Messages.REQUEST_IS_NULL;

@Service
@Slf4j
public class TransferService {
    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public String transfer(TransferRequest request) {
        String idTransfer = repository.tranfer(request);
        repository.putCode(idTransfer, "0000");
        return idTransfer;
    }

    public String confirmOperation(ConfirmOperationRequest request) {
        if (request == null) {
            throw new InputDataException(REQUEST_IS_NULL);
        }

        String operationId = request.getOperationId();

        if (request.getCode().equals(repository.getCode(operationId))) {
            return repository.confirmOperation(request);
        } else {
            throw new InputDataException(CONFIRM_CODE_IS_NOT_VALID);
        }
    }
}
