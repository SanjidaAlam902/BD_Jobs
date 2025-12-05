package com.example.bd_jobs;

public class HardwareItem {

        private String id;
        private String name;
        private String assignedTo;

        public HardwareItem() {}
        public HardwareItem(String id, String name, String assignedTo) {
            this.id = id; this.name = name; this.assignedTo = assignedTo;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getAssignedTo() { return assignedTo; }
        public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }


}
