package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository, Cloneable {

    private Author[] authors = new Author[0];


    @Override
    public boolean save(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (findByFullName(author.getName(), author.getLastName()) != null) {
                return false;
            }
        }
        Author[] authorsPlusOne = new Author[authors.length + 1];
        for (int i = 0; i < authorsPlusOne.length - 1; i++) {
            authorsPlusOne[i] = authors[i];
        }
        authorsPlusOne[authorsPlusOne.length - 1] = author;
        authors = authorsPlusOne.clone();
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }


        }
        return null;
    }

    @Override
    public boolean remove(Author author) {

        int searchElement = 0;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            for (int i = 0; i < authors.length; i++) {
                if (authors[i] == author) {
                    searchElement = i;
                    break;
                }
            }
            Author[] authorsMinus = new Author[authors.length - 1];
            System.arraycopy(authors, 0, authorsMinus, 0, searchElement);
            System.arraycopy(authors, searchElement + 1, authorsMinus, searchElement, authors.length - searchElement - 1);
            authors = new Author[authorsMinus.length];
            System.arraycopy(authorsMinus, 0, authors, 0, authorsMinus.length);
            return true;

        }



    }
    @Override
    public int count () {
        return authors.length;
    }
}
