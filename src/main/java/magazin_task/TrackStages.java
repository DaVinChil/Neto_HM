package magazin_task;

public enum TrackStages {
    NOT_ORDERED("NOT ORDERED"), PROCESSING("PROCESSING"), ACCEPTED("ACCEPTED"), ON_THE_WAY("ON THE WAY"), DELIVERED("DELIVERED");

    String toString;

    TrackStages(String toString){
        this.toString = toString;
    }

    @Override
    public String toString(){
        return toString;
    }

    public TrackStages getNextStage(){
        switch (this){
            case PROCESSING:
                return ACCEPTED;
            case ACCEPTED:
                return ON_THE_WAY;
            case ON_THE_WAY:
                return DELIVERED;
            default:
                return this;
        }
    }
}
