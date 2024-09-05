package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.dtos.RequestResDTO;
import com.guardians_of_the_code.entities.Request;

import java.util.List;
import java.util.UUID;

public interface RequestInterface {
    List<RequestResDTO> findRequestByClient(List<UUID> clientUuids);
    RequestResDTO findRequest(UUID uuid);
    Request saveRequest(RequestReqDTO request);
    MessageStatusDTO updateRequest(UUID uuid,RequestReqDTO request);
    void deleteRequest(UUID uuid);
    boolean existsRequest(UUID uuid);
}
