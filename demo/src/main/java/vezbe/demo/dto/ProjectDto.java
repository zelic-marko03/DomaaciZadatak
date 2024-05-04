package vezbe.demo.dto;

import vezbe.demo.model.Project;

import java.util.Date;

public class ProjectDto {

    private Long id;

    private String naziv;

    private Date rok;

    public ProjectDto(Long id, String naziv, Date rok) {
        this.id = id;
        this.naziv = naziv;
        this.rok = rok;
    }

    public ProjectDto(Project project){
        this.id=project.getId();
        this.naziv=project.getName();
        this.rok=project.getDeadline();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getRok() {
        return rok;
    }

    public void setRok(Date rok) {
        this.rok = rok;
    }
}
