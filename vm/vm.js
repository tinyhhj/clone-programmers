const {NodeVM} = require('vm2');
const solve = require('./test.js');

const vm = new NodeVM({
    console: 'inherit',
    sandbox: {solve},
    require: {
        external: true,
    }
});
vm.run(`
solve();
`,'/data/vm.js');
