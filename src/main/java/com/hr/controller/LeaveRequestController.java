package com.hr.controller;

import com.hr.model.LeaveRequest;
import com.hr.repository.LeaveRequestRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
@Tag(name = "Leave Management", description = "APIs for managing leave requests")
@CrossOrigin(origins = "*")
public class LeaveRequestController {

    private final LeaveRequestRepository leaveRequestRepository;

    @GetMapping
    @Operation(summary = "Get all leave requests")
    public List<LeaveRequest> getAllLeaves(@RequestParam(required = false) String status) {
        if (status != null && !status.equalsIgnoreCase("all")) {
            return leaveRequestRepository.findByStatus(status);
        }
        return leaveRequestRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Submit a new leave request")
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        leaveRequest.setStatus("Pending");
        return leaveRequestRepository.save(leaveRequest);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update leave request status")
    public ResponseEntity<LeaveRequest> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return leaveRequestRepository.findById(id)
                .map(leave -> {
                    leave.setStatus(status);
                    return ResponseEntity.ok(leaveRequestRepository.save(leave));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
