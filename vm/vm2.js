#!/usr/bin/env node

'use strict';

const { ArgumentParser } = require('argparse');
const { NodeVM} = require('vm2');
const parser = new ArgumentParser({
    description: 'parse agrs'
});

parser.add_argument('-c','--code');
parser.add_argument('-i','--input');
parser.add_argument('-o','--output');

const args = parser.parse_args();

const ext ={};
const vm = new NodeVM({console: 'inherit',

sandbox: {ext},
require: {
    external: true,
}});
vm.run(`ext.res = (${args.code}).apply({},${args.input});`);

if( `${ext.res}` === args.output) {
    console.log(`${ext.res} 정답입니다.`);
} else {
    console.log(`${ext.res} 오답입니다. (정답: ${args.output})`);
}



