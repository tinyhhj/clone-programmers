class App {
    constructor({$target}) {
        this.$target = $target;
        this.$target.innerHTML = '<aside id="test"></aside><main id="code"></main>'
        this.children = [];
        this.fetchTest()
            .then(res=>this.setState({tests:res.data}))
        this.state = {
          tests: []
        };
        this.render();
    }
    setState(state) {
        this.state = {...this.state,...state};
        this.render();
    }

    appendChild(child) {
        this.children.push(child);
    }

    fetchTest() {
        return fetch('/api/tests').then(res=>res.json());
    }

    render() {
        this.children.forEach(e=>e.render());
    }
}
class Header {
    constructor({$target}) {
        this.$target = $target;
    }
    render() {

    }
}
class Test {
    constructor({$target}) {
        this.$target = $target;
        this.state = {
            test: {}
        }
    }
    render() {
        this.state.test
    }

}
new App({$target: document.querySelector('#main')});