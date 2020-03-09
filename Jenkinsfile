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
        sh "./gradlew clean API:test"
    }
    stage("run UI test"){
        sh "./gradlew clean UI:test"
    }

    stage('Publish tests results') {
        allure([
            includeProperties: false,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'API/build/allure-results'],[path: 'UI/build/allure-results']]
        ])
    }
}