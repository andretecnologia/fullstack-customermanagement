` ng new client --skip-install --skipTests=true --skip-git`

` git init && git config core.autocrlf false `


` rm -rf .git `

` cd client `

` npm install `

` npm install @angular/material @angular/cdk `

` ng g s shared/customer/customer `

` ng g c customer-list --skipTests=true `

` ng serve --live-reload=false `

