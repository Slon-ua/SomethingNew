node {

    stage("checkout repo"){
        git branch: 'master',
        credentials: '',
        url: 'https://github.com/Slon-ua/SomethingNew.git'
    }

    stage("build"){
        sh "./gradlew clean API:assemble "
    }
    stage("run API test"){
//#        when{
//#            expression {'${TEST_TYPE}' == 'ALL'}
//#        }
//#        step {
//#            sh "./gradlew clean API:test"
//#        }
        sh "./gradlew  API:test"
    }
    stage("run UI test"){
        sh "./gradlew  UI:test"
    }

/* 
             allure([
                includeProperties: false,
                jdk              : '',
                properties       : [],
                reportBuildPolicy: 'ALWAYS',
                results          : [[path: 'API/build/allure-results'],[path: 'UI/build/allure-results']]
            ])
 */
    allure includeProperties: false, jdk: '', results: [[path: 'UI/build/allure-results'], [path: 'API/build/allure-results']]
}


