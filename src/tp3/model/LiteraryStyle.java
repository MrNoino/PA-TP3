package tp3.model;

/**
 * A class that represents a literary style
 */
public class LiteraryStyle {
    private int id;
    private String literaryStyle;

    /**
     * Class constructor that assigns the attributes
     * @param aId the literary style id
     * @param aLiteraryStyle the literary style
     */
    public LiteraryStyle(int aId, String aLiteraryStyle) {
        this.id = aId;
        this.literaryStyle = aLiteraryStyle;
    }

    /**
     * Get the literary style id
     * @return the literary style id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the literary style id
     * @param id the literary style id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the literary style
     * @return the literary style
     */
    public String getLiteraryStyle() {
        return literaryStyle;
    }

    /**
     * Sets the literary style
     * @param literaryStyle the literary style
     */
    public void setLiteraryStyle(String literaryStyle) {
        this.literaryStyle = literaryStyle;
    }
    
    /**
     * toString that return the literary style
     * @return the literary style
     */
    @Override
    public String toString(){
        return this.literaryStyle;
    }
}
