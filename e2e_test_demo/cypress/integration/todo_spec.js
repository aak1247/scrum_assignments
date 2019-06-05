/// <reference types="Cypress" />


describe('Visit Test', () => {
  it('successfully loads', () => {
    cy.visit('http://localhost:8080')
  })
})

describe('Add Test', () => {
  it('successfully added a task', () => {
    cy.visit('http://localhost:8080')
    cy.get('p').contains('Create your first todo! :)')
    cy.get('input').type('task 1')
    cy.get('.create-todo-form > button').click()
    cy.get('.todo-not-completed').should('have.length',1)
    cy.get('.todo-not-completed').contains('task 1')
  })
})

describe('Delete Test', () => {
  it('successfully add a task and remove it', () => {
    cy.visit('http://localhost:8080')
    cy.get('p').contains('Create your first todo! :)')
    cy.get('input').type('task 1')
    cy.get('.create-todo-form > button').click()
    cy.get('.todo-not-completed').should('have.length',1)
    cy.get('.todo-not-completed').contains('task 1').get('button.delete-btn').click()
    cy.get('p').contains('Create your first todo! :)')
  })
})

describe('Edit Test', () => {
  it('successfully add a task and edit it', () => {
    cy.visit('http://localhost:8080')
    cy.get('p').contains('Create your first todo! :)')
    cy.get('input').type('task 1')
    cy.get('.create-todo-form > button').click()
    cy.get('.todo-not-completed').should('have.length',1)
    cy.get('tr.todo-not-completed').contains('task 1').get('button').contains('Edit').click()
    cy.get('.todo-not-completed input').clear().type('task 2')
    cy.get('.todo-not-completed button').contains('Save').click()
    cy.get('tr.todo-not-completed').contains('task 2')
  })
})