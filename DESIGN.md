Q1 — Extensibility

I would implement the AlertListener interface in new EmailAlertListener and SmsAlertListener classes since the AlertEngine can handle new listeners without needing to be changed, these new listeners will be notified without change in any existing engine or listener code.

Q2 — Concurrency

Use atomic integer or concurrent hash map to perform subtraction of stock in one step or lock stock count.

Q3 — Persistence

I would define a relational db schema with a products table and an alerts table linked using foreign keys and use an ORM to communicate with the db. I would also change unit tests to test my db queries.
