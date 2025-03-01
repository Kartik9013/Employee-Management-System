package com.ems.model;

import java.time.LocalDate;


public class Attendance {
	private int id;
    private int employeeId;
    private String employeeName;
    private LocalDate date;
    private String status;

    // Constructor
    public Attendance(int id, int employeeId, String employeeName , LocalDate date, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.status = status;
    }

    // Getter & Setter Methods
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

	public String getEmployeeName() { return employeeName; }
	public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    
    
}
