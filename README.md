# OneToOne JPA Issue

- Request:
```
{
    "items": [
        {
            "applicant": {
                "guests": [
                    {
                        "seqNumber": 1,
                        "name": "name",
                        "gender": "gender"
                    }
                ]
            }
        }
    ]
}
```

- Expected Response:
```
{
    "items": [
        {
            "applicant": {
                "guests": [
                    {
                        "seqNumber": 1,
                        "name": "name",
                        "gender": "gender"
                    }
                ]
            }
        }
    ]
}
```
