package com.example.moboit;

public class person
{
    // Variable to store data corresponding
    // to firstname keyword in database
    private String name;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String id;

    // Variable to store data corresponding
    // to age keyword in database
    private String message;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public person() {}

    // Getter and setter method
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
}
