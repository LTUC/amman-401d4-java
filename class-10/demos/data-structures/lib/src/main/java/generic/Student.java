package generic;

public class Student<T, S> {

    private T institution;
    private S subject;

    public Student(T institution, S subject) {
        this.institution = institution;
        this.subject = subject;
    }

    public T getInstitution() {
        return institution;
    }

    public void setInstitution(T institution) {
        this.institution = institution;
    }

    public S getSubject() {
        return subject;
    }

    public void setSubject(S subject) {
        this.subject = subject;
    }
}
