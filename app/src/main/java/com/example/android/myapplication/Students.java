package com.example.android.myapplication;

class Student {
    String name;

  public String getName() {
        return name;
    }

    public String getEditor() {
        return editor;
    }

    String editor;

    public Student(String name, String editor) {
        this.name = name;
        this.editor = editor;
    }

}