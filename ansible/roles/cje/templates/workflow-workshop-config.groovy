node("cd") {
    git 'https://github.com/vfarcic/books-ms.git'
    def dir = pwd()
    sh "mkdir -p ${dir}/db"
    sh "chmod 0777 ${dir}/db"

    stage "pre-deployment tests"
    def tests = docker.image("10.100.198.200:5000/books-ms-tests")
    tests.pull()
    tests.inside("-v ${dir}/db:/data/db") {
        sh "./run_tests.sh"
    }

    stage "build"
    def service = docker.build "10.100.198.200:5000/books-ms"
    service.push()
    stash includes: "docker-compose*.yml", name: "docker-compose"
}
node("production") {
    stage "deploy"
    checkpoint "deploy"
    input message: "Please confirm deployment to production", ok: "I confirm"
    unstash "docker-compose"
    def pull = [:]
    pull["service"] = {
        docker.image("10.100.198.200:5000/books-ms").pull()
    }
    pull["db"] = {
        docker.image("mongo").pull()
    }
    parallel pull
    sh "docker-compose -f docker-compose-dev.yml -p books-ms up -d app"
    sleep 2
}
node("cd") {
    stage "post-deployment tests"
    def tests = docker.image("10.100.198.200:5000/books-ms-tests")
    tests.inside() {
        withEnv(["TEST_TYPE=integ", "DOMAIN=http://10.100.199.201:8080"]) {
            retry(1) {
                sh "./run_tests.sh"
            }
        }
    }
}