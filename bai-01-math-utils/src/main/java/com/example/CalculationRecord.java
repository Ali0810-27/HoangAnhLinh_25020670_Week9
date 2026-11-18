package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "calculation_history")
public class CalculationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private double operandA;
    private double operandB;
    private double result;

    public CalculationRecord() {
    }

    public CalculationRecord(String operation, double operandA, double operandB, double result) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public double getOperandA() {
        return operandA;
    }

    public double getOperandB() {
        return operandB;
    }

    public double getResult() {
        return result;
    }
}
