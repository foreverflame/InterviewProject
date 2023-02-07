package com.example.test.design.chains;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements StudyPrepareFilter {

    private int pos = 0;

    private Study study;

    private List<StudyPrepareFilter> studyPrepareFilterList;

    public FilterChain(Study study) {
        this.study = study;
    }

    public void addFilter(StudyPrepareFilter studyPrepareFilter) {
        if (studyPrepareFilterList == null) {
            studyPrepareFilterList = new ArrayList<StudyPrepareFilter>();
        }

        studyPrepareFilterList.add(studyPrepareFilter);
    }

    @Override
    public void doFilter(PreparationList thingList, FilterChain filterChain) {
        if (pos == studyPrepareFilterList.size()) {
            study.study();
        }

        studyPrepareFilterList.get(pos++).doFilter(thingList, filterChain);
    }

}