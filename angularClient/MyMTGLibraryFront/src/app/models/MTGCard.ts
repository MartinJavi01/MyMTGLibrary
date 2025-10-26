import { CardFace } from "./CardFace";
import { ImageUris } from "./ImageUris";
import { Prices } from "./Prices";
import { PurchaseUris } from "./PurchaseUris";
import { Set } from "./Set";

export interface MTGCard {
    id: string,
    oracle_id: string,
    name: string,
    released_at: Date,
    image_uris: ImageUris,
    mana_cost: string,
    cmc: string,
    type_line: string,
    spellType: string,
    subType: string,
    oracle_text: string,
    power: string,
    toughness: string,
    colors: string[],
    color_identity: string[],
    keywords: string[],
    rarity: string,
    set: string,
    set_id: string,
    set_name: string,
    prices: Prices,
    purchase_uris: PurchaseUris
    card_faces: CardFace[],
    setDTO: Set,
    copies: number,
    foil: boolean
}