package com.reham.modules.Requests;

public class LoginRes
{
   // {"status":false,"message":"Login failed"}

    boolean status;
    String message;
    rows rows;

    public LoginRes(boolean status, String message, LoginRes.rows rows) {
        this.status = status;
        this.message = message;
        this.rows = rows;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LoginRes.rows getRows() {
        return rows;
    }

    public class rows

    {
        private int id;

        public rows(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }


}
