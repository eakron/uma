-- name: get-users-raw
-- Find all users.
select *
from uma_user

-- name: create-user-raw<!
-- Creates a user.
insert into uma_user
  (firstName, lastName, personalIDNumber, phoneNumbers, email,
  password, username, student, streetAddress, postalCode,
  city, notes, guardians, membershipNumber)
values
  (:firstName, :lastName, :personalIDNumber, :phoneNumbers, :email,
  :password, :username, :student, :streetAddress, :postalCode,
  :city, :notes, :guardians, :membershipNumber)

-- name: get-user-by-id-raw
-- Finds a user by id.
select *
from uma_user
where id = :id

-- name: update-user-raw!
-- Update a user.
update uma_user
set firstName = :firstName,
    lastName = :lastName,
    personalIDNumber = :personalIDNumber,
    phoneNumbers = :phoneNumbers,
    email = :email,
    password = :password,
    username = :username,
    student = :student,
    streetAddress = :streetAddress,
    postalCode = :postalCode,
    city = :city,
    notes = :notes,
    guardians = :guardians,
    membershipNumber = :membershipNumber
where id = :id
