//import { describe, it, expect, cy } from 'cypress'

describe('My First Test', function () {
  it('Test of test', function () {
    expect(true).to.equal(true)
  })
})

describe('Visit Test', function () {
  it('successfully loads', function () {
    cy.visit('/')
  })
})
