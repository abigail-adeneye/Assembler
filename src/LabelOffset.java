/**
* Class (struct style) for storing a label and its relative offset from an assembly
* language program.
*
* DO NOT MODIFY THIS FILE.
*
* @author Christine Reilly
* Copyright 2024 Christine F. Reilly creilly@skidmore.edu
*/
public class LabelOffset {

    /** Label from assembly language program */
    public String label;

    /** Relative offset of the label within this assembly language program */
    public int offset;

    /**
     * Returns a string representation of this object
     */
    public String toString() {
        return(this.label + " : " + this.offset);
    }
}
