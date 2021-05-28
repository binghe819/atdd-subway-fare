package wooteco.subway.line.domain;

import wooteco.subway.station.domain.Station;

import java.util.List;

public class Line {
    private Long id;
    private String name;
    private String color;
    private int extraFare;
    private Sections sections = new Sections();

    public Line() {
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Line(String name, String color, int extraFare) {
        this.name = name;
        this.color = color;
        this.extraFare = extraFare;
    }

    public Line(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Line(Long id, String name, String color, int extraFare) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.extraFare = extraFare;
    }

    public Line(Long id, String name, String color, int extraFare, Sections sections) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.extraFare = extraFare;
        this.sections = sections;
    }

    public Line(Long id, String name, String color, Sections sections) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.sections = sections;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getExtraFare() {
        return extraFare;
    }

    public Sections getSections() {
        return sections;
    }

    public void update(Line line) {
        this.name = line.getName();
        this.color = line.getColor();
    }

    public void addSection(Station upStation, Station downStation, int distance) {
        Section section = new Section(upStation, downStation, distance);
        sections.addSection(section);
    }

    public void addSection(Section section) {
        if (section == null) {
            return;
        }
        sections.addSection(section);
    }

    public void removeSection(Station station) {
        sections.removeStation(station);
    }

    public List<Station> getStations() {
        return sections.getStations();
    }

    public boolean isExistsStation(Station station) {
        List<Station> stations = sections.getStations();
        return stations
            .stream()
            .anyMatch(it -> it.equals(station));
    }

    public boolean contains(Station station) {
        return getStations().contains(station);
    }

    public int getNextStationDistance(Station station) {
        for (Section section : sections.getSections()) {
            if (section.getUpStation().equals(station)) {
                return section.getDistance();
            }
        }
        return 0;
//        Section section = sections.getSections()
//            .stream()
//            .filter(it -> it.getUpStation().equals(station))
//            .findFirst()
//            .orElseThrow(() -> new IllegalArgumentException("해당 노선에 존재하지 않는 지하철 역입니다."));
//        return section.getDistance();
    }
}
