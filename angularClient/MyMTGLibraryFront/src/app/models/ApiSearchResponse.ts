import { MTGCard } from "./MTGCard";

export interface ApiSearchResponse {
    totalCards: number,
    hasMore: boolean,
    nextPage: string,
    data: MTGCard[]
}