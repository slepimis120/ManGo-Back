package mango.dto;

import java.util.ArrayList;

public class NoteResponseDTO {
	private Integer totalCount;
	private ArrayList<NoteDTO> notes;
	
	public NoteResponseDTO(Integer totalCount, ArrayList<NoteDTO> notes) {
		super();
		this.totalCount = totalCount;
		this.notes = notes;
	}
	
	NoteResponseDTO(){}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public ArrayList<NoteDTO> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<NoteDTO> notes) {
		this.notes = notes;
	}
	
}
