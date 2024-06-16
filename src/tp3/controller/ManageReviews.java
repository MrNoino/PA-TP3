package tp3.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * Gets the reviews of the author from the database
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
     * Gets a list of reviews in the database
     * @return a list of reviews 
     */
    public ArrayList<Review> getReviews() {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("SELECT * FROM get_reviews");

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
                this.reviews.add(new Review(resultSet.getLong("id"),
                        resultSet.getInt("random_code"),
                        resultSet.getString("serial_number"),
                        resultSet.getString("submission_date"),
                        resultSet.getString("approval_date"),
                        resultSet.getString("completion_date"),
                        resultSet.getInt("elapsed_time"),
                        resultSet.getString("observations"),
                        resultSet.getFloat("cost"),
                        new Book(resultSet.getLong("book_id"), null, null, -1, -1, null, null, null, null, -1, null, resultSet.getLong("author_id")),
                        resultSet.getLong("author_id"),
                        resultSet.getLong("manager_id"),
                        resultSet.getLong("reviewer_id"),
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
     * Gets a list of reviews provided the reviewer id
     * @param reviewerId the reviewer id
     * @return list of reviews
     */
    public ArrayList<Review> getReviewerReviews(Long reviewerId) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_reviewer_reviews(?);", new Object[]{reviewerId});

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
                this.reviews.add(new Review(resultSet.getLong("id"),
                        resultSet.getInt("random_code"),
                        resultSet.getString("serial_number"),
                        resultSet.getString("submission_date"),
                        resultSet.getString("approval_date"),
                        resultSet.getString("completion_date"),
                        resultSet.getInt("elapsed_time"),
                        resultSet.getString("observations"),
                        resultSet.getFloat("cost"),
                        new Book(resultSet.getLong("book_id"), null, null, -1, -1, null, null, null, null, -1, null, resultSet.getLong("author_id")),
                        resultSet.getLong("author_id"),
                        resultSet.getLong("manager_id"),
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

    /**
     * Updates the review in the database
     * @param id the review id
     * @param observations the observations
     * @param cost the cost
     * @param status the status
     * @return update was successful
     */
    public boolean updateReview(Long id, String observations, float cost, String status) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean updated = dbWrapper.manipulate("CALL update_review(?, ?, ?, ?)", new Object[]{id, observations, cost, status}) > 0;

        if (updated && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new Date()),
                    "Atualizou a revisão (ID: " + id + ")"));
        }

        return updated;
    }

    /**
     * Gets the reviews as an author review array to be used in a table
     * @return an array of reviews
     */
    public Object[][] toAuthorReviewArray() {
        Object[][] u = new Object[this.reviews.size()][4];

        for (int i = 0; i < this.reviews.size(); i++) {
            Review review = this.reviews.get(i);
            u[i] = new Object[]{review.getSubmissionDate(), review.getSerialNumber(), review.getBook().getTitle(), review.getStatus()};
        }

        return u;
    }

    /**
     * Gets the reviews as a reviewer review array to be used in a table
     * @return an array of reviews
     */
    public Object[][] toReviewerReviewsArray() {
        Object[][] u = new Object[this.reviews.size()][13];

        for (int i = 0; i < this.reviews.size(); i++) {
            Review review = this.reviews.get(i);
            u[i] = review.toReviewerReviewsArray();
        }

        return u;
    }

    /**
     * Gets the reviews as an array
     * @return an array of reviews
     */
    public Object[][] toArray() {
        Object[][] u = new Object[this.reviews.size()][14];

        for (int i = 0; i < this.reviews.size(); i++) {
            Review review = this.reviews.get(i);
            u[i] = review.toArray();
        }

        return u;
    }
}
