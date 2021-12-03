const { Interpreter } = require('rockstar/satriani/satriani.js')
const { readFileSync } = require('fs')
const { question } = require('readline-sync')

const src = `src/${process.argv[2]}`

const rockstar = new Interpreter()
const program = readFileSync(src)
const ast = rockstar.parse(program.toString())

const output = console.log
const input = question
const result = rockstar.run(ast, input, output)
console.log(result)
