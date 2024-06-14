package tp3.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import tp3.model.Book;
import tp3.model.DbWrapper;
import tp3.model.Log;
import tp3.model.Review;
import tp3.view.Main;

/**
 * A class to manage reviews on the database
 */
public class ManageReviews {

    private ArrayList<Review> reviews;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageReviews() {
        reviews = new ArrayList<Review>();
    }

    /**
     * Gets the reviewsof the author from the database
     *
     * @param authorId the author id
     * @param sortType the field and sort order
     * @param page page of the query
     * @return the list of reviews
     */
    public ArrayList<Review> getReviewsByAuthor(long authorId, String sortType, int page) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_reviews_by_author(?, ?, ?)", new Object[]{authorId, sortType, page});

        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Listou Revisões"));
            }

            while (resultSet.next()) {
                this.reviews.add(new Review(-1,
                        -1,
                        resultSet.getString("serial_number"),
                        resultSet.getString("submission_date"),
                        null,
                        -1,
                        null,
                        -1,
                        new Book(-1, resultSet.getString("title"), null, -1, -1, null, null, null, null, -1, null, authorId),
                        -1,
                        -1,
                        -1,
                        resultSet.getString("status")));
            }
            return this.reviews;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter as revisões\n");
        } finally {
            dbWrapper.disconnect();
        }
        return null;
    }

    /**
     * Gets reviews of the author by submission date from the database
     *
     * @param authorId id of the author
     * @param date date to search
     * @return a list of reviews
     */
    public ArrayList<Review> getReviewsByDate(long authorId, String date) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL search_author_reviews_by_date(?, ?)", new Object[]{authorId, date});

        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Pesquisou Revisões por Data: " + date));
            }

            while (resultSet.next()) {
                this.reviews.add(new Review(-1,
                        -1,
                        resultSet.getString("serial_number"),
                        resultSet.getString("submission_date"),
                        null,
                        -1,
                        null,
                        -1,
                        new Book(-1, resultSet.getString("title"), null, -1, -1, null, null, null, null, -1, null, authorId),
                        -1,
                        -1,
                        -1,
                        resultSet.getString("status")));
            }
            return this.reviews;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter as revisões\n");
        } finally {
            dbWrapper.disconnect();
        }
        return null;
    }

    /**
     * Gets reviews of the author by title from the database
     *
     * @param authorId id of the author
     * @param title title to search
     * @return a list of reviews
     */
    public ArrayList<Review> getReviewsByTitle(long authorId, String title) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL search_author_reviews_by_title(?, ?)", new Object[]{authorId, title});

        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Pesquisou Revisões por Título: " + title));
            }

            while (resultSet.next()) {
                this.reviews.add(new Review(-1,
                        -1,
                        resultSet.getString("serial_number"),
                        resultSet.getString("submission_date"),
                        null,
                        -1,
                        null,
                        -1,
                        new Book(-1, resultSet.getString("title"), null, -1, -1, null, null, null, null, -1, null, authorId),
                        -1,
                        -1,
                        -1,
                        resultSet.getString("status")));
            }
            return this.reviews;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter as revisões\n");
        } finally {
            dbWrapper.disconnect();
        }
        return null;
    }

    /**
     * Gets reviews of the author by status from the database
     *
     * @param authorId id of the author
     * @param status status to search
     * @return a list of reviews
     */
    public ArrayList<Review> getReviewsByStatus(long authorId, String status) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL search_author_reviews_by_status(?, ?)", new Object[]{authorId, status});

        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Pesquisou Revisões por Estado: " + status));
            }

            while (resultSet.next()) {
                this.reviews.add(new Review(-1,
                        -1,
                        resultSet.getString("serial_number"),
                        resultSet.getString("submission_date"),
                        null,
                        -1,
                        null,
                        -1,
                        new Book(-1, resultSet.getString("title"), null, -1, -1, null, null, null, null, -1, null, authorId),
                        -1,
                        -1,
                        -1,
                        resultSet.getString("status")));
            }
            return this.reviews;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter as revisões\n");
        } finally {
            dbWrapper.disconnect();
        }
        return null;
    }
    
    public ArrayList<Review> getReviewerReviews(Long reviewerId){
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_reviewer_reviews(?)", new Object[]{reviewerId});
        
        System.out.println(resultSet);

        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(reviewerId,
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Pesquisou Revisões Em Seu Nome: " + reviewerId));
            }

            while (resultSet.next()) {
                this.reviews.add(new Review(-1,
                        -1,
                        resultSet.getString("serial_number"),
                        resultSet.getString("submission_date"),
                        null,
                        -1,
                        null,
                        -1,
                        new Book(-1, resultSet.getString("title"), null, -1, -1, null, null, null, null, -1, null, resultSet.getLong("author_id")),
                        -1,
                        -1,
                        -1,
                        resultSet.getString("status")));
            }
            return this.reviews;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter as revisões\n");
        } finally {
            dbWrapper.disconnect();
        }
        return null;
    }

    /**
     * Inserts a review in the database
     *
     * @param bookId book id associated to the review
     * @param authorId author id associated to the review
     * @return Confirms if a review was inserted successfully
     */
    public boolean insertReview(Long bookId, Long authorId) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();

        ResultSet resultSet = dbWrapper.query("CALL get_reviews_max_id()");
        Long maxId = 0L;

        try {
            if (resultSet == null) {
                return false;
            }

            while (resultSet.next()) {
                maxId = resultSet.getLong("max");
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao obter o id mais alto\n");
            dbWrapper.disconnect();
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String serialNumber = (maxId + 1) + formatter.format(now);
        int random_code = 1 + (int) (Math.random() * 1000000);

        boolean inserted = dbWrapper.manipulate("CALL insert_review(?, ?, ?, ?)", new Object[]{random_code, serialNumber, bookId, authorId}) > 0;

        if (inserted && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                    "Inseriu Revisão"));
        }

        return inserted;
    }

    public Object[][] toAuthorReviewArray() {
        Object[][] u = new Object[this.reviews.size()][4];

        for (int i = 0; i < this.reviews.size(); i++) {
            Review review = this.reviews.get(i);
            u[i] = new Object[]{review.getSubmissionDate(), review.getSerialNumber(), review.getBook().getTitle(), review.getStatus()};
        }

        return u;
    }
}
