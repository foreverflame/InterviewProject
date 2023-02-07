package com.example.test.design.chains;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/2/7
 */
public class Test {

    public static void main(String[] args) {
        PreparationList preparationList = new PreparationList();
        preparationList.setWashFace(true);
        preparationList.setWashHair(false);
        preparationList.setHaveBreakfast(true);

        Study study = new Study();

        StudyPrepareFilter washFaceFilter = new WashFaceFilter();
        StudyPrepareFilter washHairFilter = new WashHairFilter();
        StudyPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter();

        FilterChain filterChain = new FilterChain(study);
        filterChain.addFilter(washFaceFilter);
        filterChain.addFilter(washHairFilter);
        filterChain.addFilter(haveBreakfastFilter);

        filterChain.doFilter(preparationList, filterChain);
    }
}
