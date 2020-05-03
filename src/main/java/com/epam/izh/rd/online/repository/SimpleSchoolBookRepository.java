package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>, Cloneable {

    private SchoolBook[] schoolBooks = new SchoolBook[0];


    @Override
    public boolean save(SchoolBook book) {
        int size = schoolBooks.length+1;
        schoolBooks = Arrays.copyOf(schoolBooks,size);
        schoolBooks[size-1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int count = 0;
        SchoolBook[] temp = new SchoolBook[schoolBooks.length];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                temp[count] = schoolBooks[i];
                count++;
            }
        }
        return Arrays.copyOf(temp, count);


    }

    @Override
    public boolean removeByName(String name) {
        int searchedElement = 0;
        if (findByName(name) == null) {
            return false;
        } else {
            for (int i = schoolBooks.length - 1; i >= 0; i--) {
                if (schoolBooks[i].getName().equals(name)) {
                    searchedElement = i;
                    SchoolBook[] authorsTemp = new SchoolBook[schoolBooks.length - 1];
                    System.arraycopy(schoolBooks, 0, authorsTemp, 0, searchedElement);
                    System.arraycopy(schoolBooks, searchedElement + 1, authorsTemp, searchedElement, schoolBooks.length - searchedElement - 1);
                    schoolBooks = new SchoolBook[authorsTemp.length];
                    System.arraycopy(authorsTemp, 0, schoolBooks, 0, authorsTemp.length);
                }
            }
            return true;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
